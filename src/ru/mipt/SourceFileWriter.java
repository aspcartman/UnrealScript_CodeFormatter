package ru.mipt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 04.09.13
 */
public class SourceFileWriter
{
	private FileWriter writer;

	public SourceFileWriter(File file)
	{
		try
		{
			this.writer = new FileWriter(file, false);
		}
		catch (IOException e)
		{
			throw new RuntimeException("IOException"); /* gfy */
		}
	}

	public void Write(Source source)
	{
		try
		{
			for (String s : source)
			{
				writer.write(s + '\n');
			}
			writer.flush();
			writer.close();
		}
		catch (IOException e)
		{
			throw new RuntimeException("IOException");
		}
	}
}
