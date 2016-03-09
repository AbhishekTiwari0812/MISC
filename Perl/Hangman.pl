use HangMan;
use WordList;
$c="y\n";
while($c eq "y\n" ){
	#main
	########OBJECTS##########
	my $MyHangman = {};
	my $MyWordList={};
	bless $MyWordList,"WordList";
	bless $MyHangman, "HangMan"; 
	###########Functions#######
	my $prinHang = "printHangMan";
	my $RemPart="RemoveAPart";
	my $RandWord="GetARandomWord";
	$MyHangman->$prinHang();
	$k=$MyWordList->$RandWord();
	$LengthOfWord=length($k);
	$Guessed="";
	print "\nWord to be guessed:$k\n";
	@Guessed=(1..$k);
	@ToBeGuessed=(1..$k);
	for($i=0;$i<$LengthOfWord;$i+=1)
	{
		@Guessed[$i]="_";
		@ToBeGuessed[$i]=substr($k,$i,1);
	}
	$win=$LengthOfWord;
	$lose=7;
	#print @Guessed."\nTo be guessed:@ToBeGuessed\n";
	while($win>0 and $lose>0)
	{
		print "\n";
		$inputChar=<>;
		$inputChar=substr($inputChar,0,1);
		$flag=0;
		if(length($inputChar)!=1)
		{
			print "\nYou're supposed to input only one character at a time!\n";
			continue;
		}
		#print "input char:$inputChar\n";
		for($i=0;$i<$LengthOfWord;$i+=1)
		{
		#	print "To be guessed:@ToBeGuessed[$i] and input:$inputChar\n";
			if (@ToBeGuessed[$i] eq $inputChar)
			{
		#		print "in this\n";
				@Guessed[$i]=$inputChar;
				$flag=1;
				$win-=1;
			}
		}
		print "\nGuessed:@Guessed\n";
		if ($flag==0)
		{
			$lose-=1;
			$MyHangman->$RemPart();
		}
		$MyHangman->$prinHang();
	}
	#print "\nWin value:$win\nLose value:$lose\n";
	if($win==0)
	{
		print "\nYou won!\n";
	}
	if($lose==0)
	{
		print "\nYou Lost\n";
	}
	print "Wanna play again?\t(y/n)\n";
	$c=<>;
}