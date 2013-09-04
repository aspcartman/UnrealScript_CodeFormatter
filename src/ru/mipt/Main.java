package ru.mipt;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;

public class Main {

    public static void main(String[] args) throws FileNotFoundException
    {
	    File targetDirectory = new File(args[0]);
	    if (! targetDirectory.exists())
		    throw new RuntimeException("Dir doesn't exist!"+args[0]);
	    if (! targetDirectory.isDirectory())
		    throw new RuntimeException("That's not a directory!"+args[0]);

	    Collection<File> files = getFiles(targetDirectory,args[1]);
	    SourceFormatter formatter = new SourceFormatter();

	    for (File file : files)
	    {
		    System.out.print(file.getName());
		    SourceFileReader reader = new SourceFileReader(file);
		    Source source = reader.GetSourceCode();
		    reader.Close();

		    source = formatter.Format(source);

		    SourceFileWriter writer = new SourceFileWriter(file);
		    writer.Write(source);

			System.out.println("    Done.");
	    }
    }

	private static Collection<File> getFiles(File targetDirectory, String extension)
	{
		return FileUtils.listFiles(targetDirectory, new String[] {extension}, true);
	}
}
