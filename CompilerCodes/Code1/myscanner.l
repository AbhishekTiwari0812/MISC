%{
#include "myscanner.h"
%}

%%
:	return COLON;
"db_type"	return TYPE;
"db_name"	return NAME;
"db_table_prefix"	return TABLE_PREFIX;
"db_port"	return PORT;
[1-9][0-9]{3}	return INTEGER;
[a-zA-Z][_a-zA-Z0-9]*	return IDENTIFIER;
[ \t\n]		;
.		printf("Unexpected character\n");

%%

int yywrap(void){
	return 1;
	}
