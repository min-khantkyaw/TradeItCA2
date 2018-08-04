package mapp.com.sg.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NameDialog extends AppCompatDialogFragment {

    EditText editTextName;
    NameDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //AlertDialog Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Layout Inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Build a View
        View view = inflater.inflate(R.layout.name_dialog, null);

        //initializing Views
        editTextName = view.findViewById(R.id.editTextEditName);

        builder.setView(view)
                .setTitle("Change Information")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String name = editTextName.getText().toString();


                        if (name.isEmpty()) {
                            editTextName.setError("Name required");
                            editTextName.requestFocus();
                            return;
                        }
                        listener.applyTexts(name);
                    }
                });


        //return the dialog
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        try {
            listener = (NameDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
            + "must implement NameDialogListener");
        }
        super.onAttach(context);
    }

    public interface NameDialogListener{
        void applyTexts(String name);
    }
}
