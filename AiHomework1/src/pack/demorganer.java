package pack;

public class demorganer {
	public static void main( String[] args )
	{
		String aString = new String("not (a or not b) not ( not c and b)");
		System.out.println(rewriter(aString));

	}
	public static String rewriter(String re)
	{
		int i = 0;
		boolean found = false;
		String theOut = new String();


		while((i<re.length()) && !found)
		{
			if((i + 5 < re.length()) && re.substring(i, i+5).equals("not ("))
			{
				theOut = re.substring(0,i);
				theOut += "(";
				i += 5;

				while(i<re.length() && !found)
				{

					if( (i + 2<re.length()) && (
							re.substring(i, i+2).equals("or")))
					{
						theOut += "and ";

						i += 2;
					}
					else
						if( (i + 3<re.length()) && 
								(re.substring(i, i+3).equals("and")))
						{

							theOut += "or ";

							i += 3;
						}
						else 
							if((i + 3<re.length()) && 
									(re.substring(i, i+3).equals("not")))
							{

								theOut += re.substring(i+4,i+5);
								theOut += " ";

								i += 5;
							}
							else
								if(i < re.length() && 
										(re.charAt(i) == ')'))
								{
									found = true;
									theOut += ")";
									i++;
								}

								else
									if((i + 1<re.length()) && 
											!(re.substring(i, i+1).equals(" ")))
									{
										theOut += "not ";
										theOut += re.substring(i, i+1);
										theOut += " ";

										i++;
									}
									else
										i++;
				}
			}
			else i++;
		}
		if(i<re.length())
		{

			theOut += re.substring(i);
			return rewriter(theOut);
		}
		else
			return theOut;

	}

}
