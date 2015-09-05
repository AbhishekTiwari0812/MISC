.data
.asciiz "Hello world" 
var1: .word 59
var2: .word 41


.text
main:

 lw $t2,var1
 lw $v1,var2
 add $v0,$t2,$v1
 
