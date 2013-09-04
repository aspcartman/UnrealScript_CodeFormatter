package ru.mipt;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 04.09.13
 */
public class SourceWriterTest
{
	SourceFileWriter sourceWriter;

	@Before
	public void setUp() throws Exception
	{
		File file = new File("testAssets/lol.txt");
		file.createNewFile();
		sourceWriter = new SourceFileWriter(file);
	}

	@Test
	public void testOverwriting() throws Exception
	{
		Source source = new Source(new String[]{";p;","omg","fuck"});
		sourceWriter.Write(source);

	}
}
