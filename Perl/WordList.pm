package WordList;
sub new
{
my $self={};

}
@ListOfWordsToBeGuessed=("sausage","wheat","pizza","tomato","popcorn","noodles","pastries","strawberry","muffin","fazitah");
$size=@ListOfWordsToBeGuessed;
sub GetARandomWord
{
my ($self)=@_;
$k=int(rand($size));
return @ListOfWordsToBeGuessed[$k];
}