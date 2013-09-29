package ru.mipt.LineFormatters;

import ru.mipt.InvalidSyntaxException;
import ru.mipt.LineFormatter;
import ru.mipt.Source;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 05.09.13
 */
public class CommentariesBlockFormatter implements LineFormatter
{
	int starLocation = 0;
	boolean insideACommentBlock = false;

	@Override
	public boolean Condition(Source source, int lineNumber)
	{
		String line = source.getLine(lineNumber);
		boolean aBlockStartLine = isABlockStartLine(line);
		boolean aBlockEndLine = isABlockEndLine(line);

		return aBlockStartLine || insideACommentBlock || aBlockEndLine;
	}

	private boolean isABlockEndLine(String line)
	{
		return line.contains("*/");
	}

	private boolean isABlockStartLine(String line)
	{
		return line.contains("/*");
	}

	@Override
	public void Format(Source source, int lineNumber)
	{
		String line = source.getLine(lineNumber);

		boolean aBlockStartLine = isABlockStartLine(line);
		boolean aBlockEndLine = isABlockEndLine(line);

		if ((aBlockStartLine && insideACommentBlock) || (! aBlockStartLine && aBlockEndLine && ! insideACommentBlock))
		{
			throw new InvalidSyntaxException("");
		}

		if (aBlockStartLine && ! aBlockEndLine)
		{
			starLocation = line.indexOf("*");
			insideACommentBlock = true;
			return;
		}

		if (! aBlockStartLine)
		{
			if (line.matches("\\s*?\\*.*"))
			{
				line = line.replaceFirst("\\*", "");
			}
			line = line.trim();
			line = "*" + line;
			line = spaces(starLocation) + line;
		}

		if (aBlockEndLine)
		{
			insideACommentBlock = false;
		}

		source.setLine(lineNumber, line);
	}

	private String spaces(int count)
	{
		String spaces = "";
		for (int i = 0; i < count; ++ i)
		{
			spaces = spaces + " ";
		}
		return spaces;
	}

	@Override
	public boolean NeedsRerun()
	{
		return false;
	}

	@Override
	public void Finished() throws InvalidSyntaxException
	{
		if (insideACommentBlock)
		{
			throw new InvalidSyntaxException("Finished while inside a comment block");
		}
	}
}
