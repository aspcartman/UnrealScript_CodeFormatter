package ru.mipt.LineFormatters;

import ru.mipt.InvalidSyntaxException;
import ru.mipt.LineFormatter;
import ru.mipt.Source;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 04.09.13
 */
public class OperatorAlignmentFormatter implements LineFormatter
{
	@Override
	public boolean Condition(Source source, int lineNumber)
	{
		if (isTheFirstLine(lineNumber)) /* There is going to be no such situation */
		{
			return false;
		}
		if (isTheLastLine(source, lineNumber)) /* There is going to be no such situation */
		{
			return false;
		}

		String prevLine = source.getLine(lineNumber - 1);
		String line = source.getLine(lineNumber);
		String nextLine = source.getLine(lineNumber + 1);

		if (isTheLastInEqualityBlock(prevLine, line, nextLine))
		{
			return true;
		}
		return false;
	}

	private boolean isTheLastInEqualityBlock(String prevLine, String line, String nextLine)
	{
		return line.contains("=") && !nextLine.contains("=") && prevLine.contains("=");
	}

	private boolean isTheFirstLine(int lineNumber)
	{
		return lineNumber == 0;
	}

	private boolean isTheLastLine(Source source, int lineNumber)
	{
		return lineNumber == source.size() - 1;
	}

	@Override
	public void Format(Source source, int lineNumber)
	{
		int desiredPosition = DesiredOperatorPosition(source, lineNumber);
		String line = source.getLine(lineNumber);
		while (line.contains("="))
		{
			int currentPosition = line.indexOf("=");
			line = InsertSpaces(line, currentPosition, desiredPosition);
			source.setLine(lineNumber,line);

			line = NextLine(source,lineNumber);
			lineNumber--;
		}
	}

	private String InsertSpaces(String line, int currentPosition, int desiredPosition)
	{
		int diff = desiredPosition-currentPosition;
		line = line.substring(0,currentPosition) + Spaces(diff) + line.substring(currentPosition,line.length());
		return line;
	}

	private int DesiredOperatorPosition(Source source, int lineNumber)
	{
		int operatorPosition = 0;
		String line = source.getLine(lineNumber);
		while (line.contains("="))
		{
			int position = line.indexOf("=");
			if (position > operatorPosition)
			{
				operatorPosition = position;
			}
			line = NextLine(source, lineNumber);
			lineNumber--;
		}
		return operatorPosition;
	}

	private String NextLine(Source source, int currentLine)
	{
		if (! isTheLastLine(source, currentLine - 1))
		{
			return source.getLine(currentLine - 1);
		}
		else
		{
			return "";
		}
	}

	public String Spaces(int count)
	{
		String out = "";
		for (int i = 0; i < count; ++ i)
		{
			out += " ";
		}
		return out;
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
