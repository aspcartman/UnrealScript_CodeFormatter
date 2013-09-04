package ru.mipt.LineFormatters;

import ru.mipt.InvalidSyntaxException;
import ru.mipt.LineFormatter;
import ru.mipt.Source;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 04.09.13
 */
public class OperatorIsolationFormatter implements LineFormatter
{
	@Override
	public boolean Condition(Source source, int lineNumber)
	{
		String line = source.getLine(lineNumber);
		boolean wrong = HasWrongEqualSignIsolation(line);
		boolean isBeginObjectLine = line.toLowerCase().contains("begin object");
		return wrong && !isBeginObjectLine;
	}

	private boolean HasWrongEqualSignIsolation(String line)
	{
		return line.matches(".+=.+");
	}


	@Override
	public void Format(Source source, int lineNumber)
	{
		String line = source.getLine(lineNumber);
		line = line.replaceAll("(.*?)\\s*?([!\\-\\+=]?) ?(=+)\\s*?(\\S+)", "$1 $2$3 $4");
		source.setLine(lineNumber, line);
	}

	@Override
	public boolean NeedsRerun()
	{
		return false;
	}

	@Override
	public void Finished() throws InvalidSyntaxException
	{
		/* Always ok */
	}
}
