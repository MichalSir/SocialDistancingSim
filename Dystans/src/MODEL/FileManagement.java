package MODEL;

import com.google.gson.Gson;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManagement {
     Gson gson = new Gson();
     String path = "Data\\Patogens\\";

    public  void writeJson(Patogen object)
    {
        try
        {
            Writer writer = Files.newBufferedWriter(Paths.get(path +object.name + ".json"));
            gson.toJson(object,writer);
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public  ArrayList<SimData> readJson()
    {
        final File folder = new File(path);
        ArrayList<SimData> patogens = new ArrayList<SimData>();

        for(final File fileEntry: folder.listFiles())
        {
            try {
                String tempPath  = path + fileEntry.getName();
                Reader reader = Files.newBufferedReader(Paths.get(tempPath));
                Patogen temp = gson.fromJson(reader, Patogen.class);
                patogens.add(temp);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return patogens;
    }

    public static void main(String[] args) {

    }

}
