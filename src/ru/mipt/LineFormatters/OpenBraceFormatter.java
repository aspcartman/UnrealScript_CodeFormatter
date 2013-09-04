package ru.mipt.LineFormatters;

import ru.mipt.InvalidSyntaxException;
import ru.mipt.LineFormatter;
import ru.mipt.Source;

import java.util.Arrays;

/**
 * Moves an open brace to a separate line.
 */
public class OpenBraceFormatter implements LineFormatter
{
	@Override
	public boolean Condition(Source source, int lineNumber)
	{
		String line = source.getLine(lineNumber);
		return line.matches("((.?\\{.+)|(.+\\{.?))");
	}

	@Override
	public void Format(Source source, int lineNumber)
	{
		String line = source.getLine(lineNumber);
		String[] lines = line.split("((?<=\\{)|(?=\\{))");
		lines = EmptyLineSplittingFix(lines);
		source.removeLine(lineNumber);
		source.insertLines(lineNumber, lines);
	}

	private String[] EmptyLineSplittingFix(String[] lines)
	{
		if (lines[0].equals(""))
			lines = Arrays.copyOfRange(lines, 1, lines.length);
		return lines;
	}

	@Override
	public boolean NeedsRerun()
	{
		return false;
	}

	@Override
	public void Finished() throws InvalidSyntaxException
	{
		/* Always Ok */
	}
}
