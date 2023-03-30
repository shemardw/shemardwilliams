%Question 1

%SHEMAR WILLIAMS - 1704317 

taxable:-nl,write('Enter name: '),nl,read(Name),nl,write('Enter Tax revenue Number: '),nl,read(Trn),nl,write('Enter income: '),nl,read(Income),nl,write('Are you married y/n?: '),nl,read(MaritalStatus),calculate_taxpayable(Income,TaxPayable),get_refund(MaritalStatus,TaxRefund),display_output(Name,Trn,Income,TaxRefund,TaxPayable).

calculate_taxpayable(Income,TaxPayable):-Income>=1,Income<5462->TaxPayable=0;Income>=5462,Income<18894->TaxPayable is Income*0.1;Income>=18894,Income<39500->TaxPayable is Income*0.15;Income>=39500,Income<55900->TaxPayable is Income*0.2;Income>=55900->TaxPayable is Income*0.25.

get_refund(MaritalStatus,TaxRefund):- MaritalStatus=='y' ->TaxRefund is 50; MaritalStatus=='n'->TaxRefund is 60.

display_output(Name,Trn,Income,TaxRefund,TaxPayable):-nl,write('*****Tax Payable Report*****'),
    DisposableIncome is Income-(TaxPayable+TaxRefund),
    nl,write('Name: '),write(Name),
    nl,write('TRN: '),write(Trn),
    nl,write('Taxable Income: '),write(Income),
    nl,write('Taxes: '),write(TaxPayable),
    nl,write('Refund: '),write(TaxRefund),
    nl,write('Disposable Income: '),write(DisposableIncome).

%Question 2


car_dealers(zeus).
car_dealers(staub).
car_dealers(mercury).

type(zeus,reasonable).
type(staub,reasonable).
type(mercury,expensive).


originated_in(zeus,usa).
originated_in(mercury,norway).
originated_in(staub,japan).

ships_to(zeus,norway).
ships_to(zeus,japan).
ships_to(zeus,usa).


ships_to(mercury,japan).
ships_to(mercury,germany).

ships_to(staub,germany).
ships_to(staub,usa).

sells(staub,mazda).
sells(staub,toyota).

sells(zeus,mazda).
sells(zeus,toyota).

sells(mercury,hyundai).

will_buy(lisa,mazda).
will_buy(lisa,toyota).

will_buy(tanya,mazda).
will_buy(tanya,toyota).

will_buy(thomas,hyundai).

company_name(lisa,Company):-type(Company,reasonable),\+ originated_in(Company,japan),ships_to(Company,germany);ships_to(Company,usa),sells(Company,toyota);sells(Company,mazda).

company_name(tanya,Company):-type(Company,reasonable),ships_to(Company,germany),sells(Company,toyota);sells(Company,mazda).

company_name(thomas,Company):-ships_to(Company,germany),sells(Company,hyundai).


car_details(Buyer):-company_name(Buyer,Company),will_buy(Buyer,Car),nl,write(Buyer),write(' can purchase '),write(Car),write(' from '),write(Company),fail.

display:-car_details(lisa);car_details(tanya);car_details(thomas).
