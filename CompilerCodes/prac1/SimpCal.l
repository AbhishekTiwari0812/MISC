%{
#include<stdlib.h>
#include<stdio.h>
#include "y.tab.h"
void yyerror(char *);
%}
digit [0-9]
%%
{digit}+	{
		yylval=atoi(yytext);
		return INT_TOKEN;
		}
[-+\n]	{ /*returns operator*/ return *yytext;		}
[ \t]		{;}
.		yyerror("invalid character\n");
%%
int yywrap(void )
{
return 1;
}

/*int main(int argc,char* argv)
{
if(argc>=2)
	yyin=fopen(argv[1],"r");
else{
	printf("Enter the name of input file\n!");
	return 0;
	}
if(argc>=3)
	yyout=fopen(argv[2],"w");
else{
	printf("specify the name of output file\n!");
	return 0;	
	}
yylex();
return 0;
}*/
