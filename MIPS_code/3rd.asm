.data 
myArray: .word 0:40
h: .word 7
.text 
main:
# myArray[12]=myArray[8]+h
lw $8,h
la $s0,myArray		#to load address from somewhere
addi $9,$9,10		
sw $9,4($s0)		#makes myArray[1]=10
la $10,8($s0)
la $s0,myArray
lw $s0,4($s0)
add $10,$8,$s0
