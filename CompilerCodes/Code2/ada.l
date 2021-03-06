%{
/*  using atoi so we have to declare stdlib here*/
#include<stdlib.h>
//declaring the non terminals now
//we have to enclose c header declarations with  %{%}
//notice how . is being used in REAL!
//yytext holds current lexeme 

%}
DIGIT	[0-9]
ID	[a-zA-Z][a-zA-Z0-9]*
INT	{DIGIT}+
REAL	{DIGIT}*"."{DIGIT}+
%%

{INT}	{printf("An integer: %s -> (%d)\n ",yytext,atoi(yytext) );}

{REAL}	{printf("A real is encountered:%s\tvalue:%g",yytext,atof(yytext));}

if	{printf("The keyword %s is encountered",yytext);}

{ID}	{printf("ID encountered:%s",yytext);}

[ \t\n]+		/*Ignore whitespaces!*/

.		{printf("Unrecognised character:%s\n",yytext);}

%%

int yywrap()
{return -1;}
// we can redefine main here.
//we just have to call yylex() at the first line of the main function
//or override any other function for that matter








