package ru.mipt;

import ru.mipt.LineFormatters.*;

import java.util.ArrayList;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 03.09.13
 */
public class SourceFormatter
{
	Source source;
	ArrayList<LineFormatter> formatters;

	public SourceFormatter()
	{
		formatters = new ArrayList<LineFormatter>();
		formatters.add(new TrimFormatter());
		formatters.add(new ExcessLinesFormatter());
		formatters.add(new OpenBraceFormatter());
		formatters.add(new CloseBraceFormatter());
		formatters.add(new ExcessSpacesFormatter());
		formatters.add(new BracesIndentationFormatter());
		formatters.add(new BeginEndIndentationFormatter());
	}

	public SourceFormatter(ArrayList<LineFormatter> formatters)
	{
		this.formatters = formatters;
	}

	public SourceFormatter(LineFormatter formatter)
	{
		ArrayList<LineFormatter> list = new ArrayList<LineFormatter>();
		list.add(formatter);
		this.formatters = list;
	}

	public Source Format(Source inputSource) throws InvalidSyntaxException
	{
		source = (Source) inputSource.clone();

		for (int i = 0; i < source.size(); ++ i)
		{
			boolean needsRerun = false;

			for (LineFormatter formatter : formatters)
			{
				if (! formatter.Condition(source, i))
				{
					continue;
				}

				formatter.Format(source, i);

				if (formatter.NeedsRerun())
				{
					needsRerun = true;
					break;
				}
			}

			if (needsRerun)
			{
				i--;
			}
		}

		for (LineFormatter formatter : formatters)
		{
			formatter.Finished();
		}

		return source;
	}
}
