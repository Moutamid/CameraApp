package com.moutamid.cameraapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.fxn.stash.Stash;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class NetworkTask extends AsyncTask<String, Void, Boolean> {
    private Activity mContext;

    public NetworkTask(Activity context) {
        mContext = context;
    }

    @Override
    protected Boolean doInBackground(String... imageUris) {
        try {
            final String username = "arbkan05@gmail.com";
            final String password = "trmjndgxgbjvfegj";
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("arbkan05@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(Stash.getString("email")));
            message.setSubject("Phone No: " + Stash.getString("phone"));


            Multipart multipart = new MimeMultipart();

            // Get the single image URI from the parameters
            String imageUri = imageUris[0];
            Log.d("images ", imageUri + "  1");
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            try {
                DataSource source = new FileDataSource(imageUri);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName("image.jpg");
                multipart.addBodyPart(attachmentBodyPart);
            } catch (Exception e) {
                Log.e("SendEmailTask", "Error sending image", e);
            }

            message.setContent(multipart);
            Transport.send(message);
            return true;
        } catch (Exception e) {
            Log.e("SendEmailTask", "Error sending email", e);
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            // Email sent successfully, show success toast
            Toast.makeText(mContext, "Email sent successfully!", Toast.LENGTH_SHORT).show();
            mContext.startActivity(new Intent(mContext, MainActivity.class));
            mContext.finishAffinity();
        } else {
            // Error sending email, show error toast
            Toast.makeText(mContext, "Something went wrong, Please try again later", Toast.LENGTH_SHORT).show();
        }
    }
}
