.data 
A: .word 54 
Myarray: .byte 0:100		#creates an character array of size 100
My_intArray: .word 0:36 		#creates an integer array of size 36
.asciiz "Hello world"
.text
main:
#subi $s0,$s0,-100
lw $v0 ,A
li $t0,100
add $t0,$t0,$v0
#coding following c program in MIPS
#int array[12];
#int x=3;
#array[x]=-23;
#declare an array in .data region first
la $t0,Myarray			#loads array "Myarray" base address
li $t1,3			#$t0 stores the value of x
mul $t2,$t1,4			#calculates the offset value viz 4(word space)*3(index to be accessed )
add $t1,$t0,$t2			#now $t0 contains address of A[3]
li $t0,-23 			#finally A[3]=-23
sw $t0,($t1)			#written back to actual memory address,permanent store.
