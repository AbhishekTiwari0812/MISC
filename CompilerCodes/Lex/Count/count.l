%{
//writing code to count number of identifiers

#include<stdlib.h>
#include<stdio.h>
int identCount,intCount;
%}
DIGIT	 [0-9]
IDENTIFIER	[a-zA-Z][_a-zA-Z0-9]*

%%
[1-9]{DIGIT}* 		{printf("%d is integer \tIntCount=%d",atoi(yytext),++intCount);}
{IDENTIFIER}	{printf("%s is identifier\tIdentCount=%d",yytext,++identCount);}

%%
int yywrap()
{
return 1;
}

int main(int argc,char* argv[])
{
//yyout=fopen(argv[1],"w");
yylex();
//fclose(yyout);
return 0;
}
