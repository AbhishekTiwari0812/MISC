package HangMan;
sub new{
my $self={};
}
$NumBodyParts=7;
$Head="O";
$Chest="|";
$LeftHand="\\";
$RightHand="/";
$Stomach="|";
$LeftLeg="/";
$RightLeg="\\";
sub RemoveAPart
{
my ($self)=@_;
$NumBodyParts-=1;
}
sub printHangMan
{
my ($self)=@_;
if($NumBodyParts<7)
{$RightLeg="";}
if($NumBodyParts<6)
{$LeftLeg="";}
if($NumBodyParts<5)
{$Stomach="";}
if($NumBodyParts<4)
{$RightHand="";}
if($NumBodyParts<3)
{$LeftHand=" ";}
if($NumBodyParts<2)
{$Chest="";}
if($NumBodyParts<1)
{$Head="";}
print "	 _____";
print "\n";
print "	|     |";
print "\n";
print "	|     ".$Head;
print "\n";
print "	|    ".$LeftHand.$Chest.$RightHand;
print "\n";
print "	|     ".$Stomach;
print "\n";
print "	|    ".$LeftLeg." ".$RightLeg;
print "\n";
print "	|";
print "\n";
print "    ____|______";
}