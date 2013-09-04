package ru.mipt;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SourceFormatterTest
{
	SourceFormatter sourceFormatter;

	@Before
	public void setUp() throws Exception
	{
		sourceFormatter = new SourceFormatter();
	}

	@After
	public void tearDown() throws Exception
	{
		sourceFormatter = null;
	}

	@Test
	public void testTrimming() throws Exception
	{
		String[] input = new String[] {"  ", "   some", "\tcode  ", "\t"};
		String[] expected = new String[] {"", "some", "code", ""};
		Source inputSource = new Source(input);
		Source expectedSource = new Source(expected);

		Source actualSource = sourceFormatter.Format(inputSource);

		Assert.assertEquals(expectedSource, actualSource);
	}

	@Test
	public void testRemovingExcessChars() throws Exception
	{
		String[] input = new String[] {"l   l", "l        l"};
		String[] expected = new String[] {"l l", "l l"};
		Source inputSource = new Source(input);
		Source expectedSource = new Source(expected);

		Source actualSource = sourceFormatter.Format(inputSource);

		Assert.assertEquals(expectedSource, actualSource);
	}

	@Test
	public void testRemovingExcessEmptyLines() throws Exception
	{
		String[] input = new String[] {"lol", "   ", "\t", "", "lol"};
		String[] expected = new String[] {"lol", "", "lol"};
		Source inputSource = new Source(input);
		Source expectedSource = new Source(expected);

		Source actualSource = sourceFormatter.Format(inputSource);

		Assert.assertEquals(expectedSource, actualSource);
	}

	@Test
	public void testIndentationBraces() throws Exception
	{
		String[] input = new String[] {"lol", "{", "lol", "}", "lol"};
		String[] expected = new String[] {"lol", "{", "\tlol", "}", "lol"};
		Source inputSource = new Source(input);
		Source expectedSource = new Source(expected);

		Source actualSource = sourceFormatter.Format(inputSource);

		Assert.assertEquals(expectedSource, actualSource);
	}

	@Test
	public void testIndentationBeginEnd() throws Exception
	{
		String[] input = new String[] {"lol", "Begin O", "begin o", "lol", "end o", "End O", "lol"};
		String[] expected = new String[] {"lol", "Begin O", "\tbegin o", "\t\tlol", "\tend o", "End O", "lol"};
		Source inputSource = new Source(input);
		Source expectedSource = new Source(expected);

		Source actualSource = sourceFormatter.Format(inputSource);

		Assert.assertEquals(expectedSource, actualSource);
	}



	@Test(expected = InvalidSyntaxException.class)
	public void testSyntaxErrorIndentationBraces() throws Exception
	{
		String[] input = new String[] {"lol", "m{", "lol", "}", "}", "lol"};
		Source inputSource = new Source(input);

		sourceFormatter.Format(inputSource);
	}

	@Test(expected = InvalidSyntaxException.class)
	public void testSyntaxErrorIndentationTwoBraces() throws Exception
	{
		String[] input = new String[] {"lol", "m{", "lol", "}}", "lol"};
		Source inputSource = new Source(input);

		sourceFormatter.Format(inputSource);
	}

	@Test /* А как же сделать тесты независимыми? Отвалится табуляция, отвалится и это. */
	public void testOpenBraceOnEmptyLine() throws Exception
	{
		String[] input = new String[] {"someFunction(){","o{{ololo","}","}","}"};
		String[] expected = new String[] {"someFunction()","{","\to","\t{","\t\t{","\t\t\tololo","\t\t}","\t}","}"};
		Source inputSource = new Source(input);
		Source expectedSource = new Source(expected);

		Source actualSource = sourceFormatter.Format(inputSource);

		Assert.assertEquals(expectedSource, actualSource);
	}

	@Test /* Вот как вариант... но в данном случае... кривоватенько */
	public void testOpenCloseBraceOnEmptyLine() throws Exception
	{
		String[] input = new String[] {"o{o{o}o}o"};
		Source inputSource = new Source(input);

		Source actualSource = sourceFormatter.Format(inputSource);

		Assert.assertEquals(input[0].length(),actualSource.size());
		int countOfBraces=0;
		for (String s : actualSource)
		{
			if (s.contains("{") || s.contains("}"))
				countOfBraces++;
		}
		Assert.assertEquals(4, countOfBraces);
	}

	@Test
	public void testCloseBraceOnEmptyLine() throws Exception
	{

		String[] input = new String[] {"someFunction()","{","\t{","\t\t{","\t\t\tolo}o}}"};
		String[] expected = new String[] {"someFunction()","{","\t{","\t\t{","\t\t\tolo","\t\t}","\t\to","\t}","}"};
		Source inputSource = new Source(input);
		Source expectedSource = new Source(expected);

		Source actualSource = sourceFormatter.Format(inputSource);

		Assert.assertEquals(expectedSource, actualSource);
	}
}
