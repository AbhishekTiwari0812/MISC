%{
#include<stdio.h>
#include<stdlib.h>
int yylex(void);	//use to get tokens and lvalues
void yyerror(char *);
%}
%token INT_TOKEN

%%
program	:	program expr '\n'	{ 	printf("%d\n",$2);	}
		|
		;
expr	:	INT_TOKEN			{	$$=$1;	}
		|	expr '+' INT_TOKEN		{	$$=$1+$3;	}
		|	expr '-' INT_TOKEN		{	$$=$1-$3;	}
		;


%%
void yyerror(char* s)
{
fprintf(stderr,"%s\n",s);
return ;
}
int main()
{
yyparse();
return 0;
}
