package np.com.naxa.iset.utils.imageutils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

public class ImageSaveTask  extends AsyncTask< String, Void, Void> {
    private Context context;

    public ImageSaveTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {
        if (params == null || params.length < 2) {
            throw new IllegalArgumentException("You should offer 2 params, the first for the image source url, and the other for the destination file save path");
        }

        String src = params[0];
        String dst = params[1];

        try {
//            File file = Glide.with(context)
//                    .load(src)
//                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//                    .get();

            File file = Glide.with(context)
                    .load(new File(src))
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();



            File dstFile = new File(dst);
            if (!dstFile.exists()) {
                boolean success = dstFile.createNewFile();
                if (!success) {
                    return null;
                }
            }

            InputStream in = null;
            OutputStream out = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));
                out = new BufferedOutputStream(new FileOutputStream(dst));

                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.flush();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            Log.d("ImageSaveTask", "doInBackground: ReportActivity Image file saved successfully ");
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}