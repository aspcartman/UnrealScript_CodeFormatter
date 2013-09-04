package ru.mipt;

import java.io.*;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 03.09.13
 */
public class SourceFileReader
{
	private LineNumberReader reader;

	public SourceFileReader(String path) throws FileNotFoundException
	{
		File file = new File(path);
		OpenReader(file);
	}

	public SourceFileReader(File file) throws FileNotFoundException
	{
		OpenReader(file);
	}


	private void OpenReader(File file)
	{
		try
		{
			FileReader fileReader = new FileReader(file);
			reader = new LineNumberReader(fileReader);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(- 1);
		}
	}

	public Source GetSourceCode()
	{
		Source source = new Source();

		String line;
		while ((line = ReadLine()) != null)
		{
			source.add(line);
		}

		return source;
	}


	private String ReadLine()
	{
		try
		{
			return reader.readLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(- 1);
		}
		return null;
	}

	public void Close()
	{
		CloseReader();
	}

	private void CloseReader()
	{
		try
		{
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


}
