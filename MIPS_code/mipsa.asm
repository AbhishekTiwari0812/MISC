.data 
My_array: .word 0:10		#declares an int array of size 40
x: .asciiz "\nEnter any number then enter any character\n"
y: .byte '\0'
z: .word   0
w: .asciiz "\nYou entered\n"
.text
print:
	li $v0,4
	la $a0,x
	syscall		#prints "Enter number"
	li $v0,5	#takes int input and stores in $v0
	syscall
	move $t0,$v0	
	li $v0,11
	syscall
	move $t1,$a0
	li $v0,4
	la $a0,w
	syscall
	li $v0,1
	move $a0,$t0
	syscall
	li $v0,11
	move $a0,$t1
	syscall
	
	

main:
	li $t0,0	#creating a for loop to add first 16 natural numbers and storing them in $v0
	li $t1,0 
	loop:
		addi $t0,$t0,1
		add $t1,$t1,$t0
		bne $t0,16,loop
	move $v0,$t1
	#bge
	#beq
	#slt
	#bne
	#blt
	#jal print
	