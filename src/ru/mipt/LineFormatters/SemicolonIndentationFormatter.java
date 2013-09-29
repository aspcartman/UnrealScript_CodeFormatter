package ru.mipt.LineFormatters;

import ru.mipt.InvalidSyntaxException;
import ru.mipt.LineFormatter;
import ru.mipt.Source;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 04.09.13
 */
public class SemicolonIndentationFormatter implements LineFormatter
{
	@Override
	public boolean Condition(Source source, int lineNumber)
	{
		if (lineNumber == 0)
			return false;

		String line = source.getLine(lineNumber);
		String prev = source.getLine(lineNumber-1);

		boolean prevLineHasSC = prev.contains(";");
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void Format(Source source, int lineNumber)
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean NeedsRerun()
	{
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void Finished() throws InvalidSyntaxException
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
