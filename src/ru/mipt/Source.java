package ru.mipt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 03.09.13
 */
public class Source extends ArrayList<String>
{
	public Source()
	{
		super();
	}

	public Source(String[] strings)
	{
		super();
		Collections.addAll(this, strings);
	}

	public boolean addLine(String line)
	{
		return this.add(line);
	}

	public String getLine(int i)
	{
		return this.get(i);
	}

	public void setLine(int i, String string)
	{
		this.set(i, string);
	}

	public void removeLine(int i)
	{
		this.remove(i);
	}

	public void insertLine(int i, String string)
	{
		this.add(i, string);
	}

	public void insertLines(int i, String[] strings)
	{
		this.addAll(i, Arrays.asList(strings));
	}
}
