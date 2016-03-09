%{
#include<stdio.h>		//for printf 
#include<stdlib.h>	//for fprintf 
extern int yylex();		//for calling the lexical analyzer
void yyerror(char *);	//something to print when there's an error
%}

%union{float f;}		//data type of the terminal token getting from lex.yy.c
%token <f> NUM		//defining data type of token
%type <f>  E  T  F		//defining type of non terminals

%%
S	:	E		{	printf("%f\n",$1);/*	C statement to be executed when the given statement is encountered*/	}		
	;
E	:	E '+' T	{ $$=$1+$3;}
	|	E'-'T		{ $$=$1+$3;}
	|	T		{$$=$1;}
	;
T	: 	T '*' F	{ $$=$1*$3;}
	|	T '/' F	{ $$=$1/$3;}
	|	F		{ $$=$1;}
	;
F	:	'(' E ')'	{$$=$2;}
	|	'-' F		{$$=-$2;}
	|	NUM	{$$ = $1;}
	;
%%

void yyerror(char *msg)
{
	fprintf(stderr,"%s\n",msg);		//printing error msg
}
int main()
{
	yyparse();		//calling the parser to execute task.calls the function which executes the parse tree.
	return 0;
}
