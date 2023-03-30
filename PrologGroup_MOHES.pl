/* 
Demali Gregg- 1903950
Deondra Brown - 2004213
Pete Aris - 1704057
Shemar Williams - 1704317 
*/ 

:- use_module(libraby(pce)).

:- dynamic symptom/2.
:- dynamic sick_patient/6. 
:- dynamic mild_count/1. 
:- dynamic severe_count/1. 
:- dynamic gamma_count/1. 
:- dynamic omni_count/1. 
:- dynamic omni_underlyingcount/1.
:- dynamic total/1. 
:- dynamic lowbloodpressure_count/1. 

/*these values should be pulled from storage */
mild_count(0).
severe_count(0).
gamma_count(0).
omni_count(0).
omni_underlyingcount(0).
total(0).
lowbloodpressure_count(0). 

% set of statistics (s_stats) that contains the number of persons
% diagnosed with mild, severe, gamma, omni and omni with underlying related symptoms.

%s_stats(0,0,0,0,0).

/* COVID-19 General Symptoms:
1. Fever 
2. Cough 
3. Lack of appetite

GAMMA variant Symptoms: 
1. severe coryza
2. severe headache 

OMNI variant Symptoms: 
1. runny nose 
2. sore throat 
3. fatigue 
/*

/* Symptoms PROLOG instructions */
symptom('systol_lower_than_90', 'low_blood_pressure'). 
symptom('diastol_lower_than_60', 'low_blood_pressure'). 

/* GAMMA variant (severe) Symptoms PROLOG instructions */ 
severe('severe coryza').
severe('severe headache'). 

/* Mild COVID-19 Symptoms */ 
mild('fever'). 
mild('cough').
mild('lack of appetite'). 
mild('runny nose').
mild ('sore throat').
mild('fatigue').

menu:- 
 new(M,dialog('MINISTRY OF HEALTH JAM-COVID-19 DIAGNOSIS EXPERT SYSTEM - MAIN MENU'))
 ,send(M,append,new(label)),
 send(M,append,button(add_reg_fact, message(@prolog,add_patient_symptom,'reg_covid'))),
 send(M,append,button(add_gamma_variant_fact, message(@prolog,add_patient_symptom, 'gamma_covid'))),
 send(M,append,button(add_omni_variant_fact, message(@prolog,add_patient_symptom, 'omni_covid'))),
 send(M,append,button(add_patient_diagnosis, message(@prolog,mainmenu))),
 send(M,append,button(moh_statistics,message(@prolog, moh_statistics))),send(M,open).
 
 add_patient_symptom(Illness):- 
	new(Z,dialog('Add Patient Symptom')),
	send(Z,append,new(label)),
	send(Z,append,new(Symptom,text_item(symptom))), 
	send(Z,append, button(accept,message(@prolog,save_symptom,Symptom?selection,Illness))),
	send(Z,open).
	
mainmenu:- 
	new(P,dialog('Patient Diagnosis Interface')),send(P,append,new(label)),
    send(P,append,new(Name,text_item(name))),
    send(P,append,new(Age,text_item(age))),
    send(P,append,new(Fever,menu(fever,marked))),
    send(P,append,new(Cough,menu(cough,marked))),
    send(P, append, new(LackofAppetite, menu('lack of appetite', marked))),
    send(P, append, new(SevereCoryza, menu('severe coryza', marked))),
    send(P, append, new(SevereHeadache, menu('severe headache', marked))),
    send(P, append, new(RunnyNose, menu('runny nose', marked))),
    send(P, append, new(SoreThroat, menu('sore throat', marked))),
    send(P, append, new(Fatigue, menu(fatigue, marked))),
    send(P, append, new(Dizziness, menu(dizziness, marked))),
    send(P, append, new(Fainting, menu(fainting, marked))),
    send(P, append, new(BlurredVision, menu('blurred vision', marked))),
    send(Fever,append,yes), send(Fever,append,no),
    send(Fatigue, append, yes), send(Fatigue, append, no),
    send(LackofAppetite, append, yes), send(LackofAppetite, append, no),
    send(SevereCoryza, append, yes), send(SevereCoryza, append, no),
    send(SevereHeadache, append, yes), send(SevereHeadache, append, no),
    send(RunnyNose, append, yes), send(RunnyNose, append, no),
    send(SoreThroat, append, yes), send(SoreThroat, append, no),
	send(Fatigue,append,yes), send(Fatigue,append,no),
    send(Dizziness, append, yes), send(Dizziness, append, no),
    send(Fainting, append, yes), send(Fainting, append, no),
    send(BlurredVision, append, yes), send(BlurredVision, append, no),
    send(Age,type,int),
    send(P,append,new(Temperature, text_item('Temperature in Fahrenheit'))),
    send(Temperature, type, int),
    send(P,append,new(Systolic, text_item('Systolic Reading'))),
    send(Systolic, type, int),
    send(P,append,new(Diastolic, text_item('Diastolic Reading'))),
    send(Diastolic, type, int),
	
	send(C,append,button(accept,message(@prolog,patient_savefile,Name?selection,
	Age?selection, 
	Fever?selection, 
	Cough?selection,
	LackofAppetite?selection,
	SevereCoryza?selection, 
	SevereHeadache?selection,
	RunnyNose?selection, 
	SoreThroat?selection, 
	Fatigue?selection, 
	Dizziness?selection, 
	Fainting?selection,
	BlurredVision?selection, 
	Temperature?selection, 
	Systolic?selection, 
	Diastolic?selection))),send(C,open).
	
	
moh_statistics:- 
		new(H,dialog('Ministry of Health COVID-19 Statistics')),send(H,append,new(label)),
		mild_count(MildCount), 
		severe_count(SevereCount),
		gamma_count(GammaCount), 
		omni_count(OmniCount), 
		omni_underlyingcount(OmniUnderCount), 
		total(Total), 
		percentage_stat(MildCount,Total,MildPercentage), 
		percentage_stat(SevereCount, Total, SeverePercentage), 
		percentage_stat(GammaCount, Total, GammaPercentage), 
		percentage_stat (OmniCount, Total, OmniPercentage), 
		percentage_stat (OmniUnderCount, Total, OmniUnderPercentage), 
		
		write('======MOH STATISTICAL REPORT======'),
		nl,
		nl,
		write('The percentage of patients with MILD symptoms are: '), write(MildPercentage), write('%'),
		nl,
		nl,
		write('The percentage of patients with SEVERE symptoms are: '), write(SeverePercentage), write('%'),
		nl,
		nl,
		write('The percentage of patients with GAMMA covid variant are: '), write(GammaPercentagePercentage), write('%'),
		nl,
		nl,
		write('The percentage of patients with OMNICRON covid variant are: '), write(OmniPercentage), write('%'),
		nl,
		nl,
		write('The percentage of patients with OMNICRON covid variant with underlying symptoms are: '), write(OmniUnderPercentage), write('%'),
		nl,
		send(H,open).
	
patient_savefile(Name, Age, Fever, Cough, LackofAppetite, SevereCoryza, SevereHeadache, RunnyNose, SoreThroat, Fatigue, Dizziness, Fainting, BlurredVision, Temperature, Systolic, Diastolic):- 
	%temperature conversion from Fahrenheit to Celsius 
	
	CTemp is (Temperature - 32) * 0.5556, 
	
	%Patient Records Display Report
    write('===PATIENT RECORDS REPORT==='), nl,
    write('Name: '), write(Name), nl,
    write('Age: '), write(Age), nl,
    write('Fever: '), write(Fever), nl,
    write('Cough: '), write(Cough), nl,
    write('Tiredness: '), write(Tiredness), nl,
    write('Severe Coryza: '), write(SevereCoryza), nl,
    write('Severe Headache: '), write(SevereHeadache), nl,
    write('Runny Nose: '), write(RunnyNose), nl,
    write('Sore Throat: '), write(SoreThroat), nl,
    write('Fatigue: '), write(Fatigue), nl,
    write('Dizziness: '), write(Dizziness), nl,
    write('Fainting: '), write(Fainting), nl,
    write('BlurredVision: '), write(BlurredVision), nl,
    write('Temperature(in Celsius): '), write(CTemp), nl,
    write('Systolic: '), write(Systolic), nl,
    write('Diastolic: '), write(Diastolic), nl,
    write('======================================='), nl,
    nl,
	
	% open('PatientsListing.txt', append, Out),
    write(Out, 'Name: '), write(Out, Name), nl(Out),
    write(Out, 'Age: '), write(Out, Age), nl(Out),
    write(Out, 'Fever: '), write(Out, Fever), nl(Out),
    write(Out, 'Cough: '), write(Out, Cough), nl(Out),
    write(Out, 'Tiredness: '), write(Out, Tiredness), nl(Out),
    write(Out, 'Severe Coryza: '), write(Out, SevereCoryza), nl(Out),
    write(Out, 'Severe Headache: '), write(Out, SevereHeadache), nl(Out),
    write(Out, 'Runny Nose: '), write(Out, RunnyNose), nl(Out),
    write(Out, 'Sore Throat: '), write(Out, SoreThroat), nl(Out),
    write(Out, 'Fatigue: '), write(Out, Fatigue), nl(Out),
    write(Out, 'Dizziness: '), write(Out, Dizziness), nl(Out),
    write(Out, 'Fainting: '), write(Out, Fainting), nl(Out),
    write(Out, 'Blurred Vision: '), write(Out, BlurredVision), nl(Out),
    write(Out, 'Temperature (in Celsius): '), write(Out, CTemp), nl(Out),
    write(Out, 'Systolic: '), write(Out, Systolic), nl(Out),
    write(Out, 'Diastolic: '), write(Out, Diastolic), nl(Out),
    write(Out, '======================================='), nl(Out),
    close(Out),
	
	  /* Determine if patient has mild COVID-19 symptoms */
    ( (Fever = 'yes'; Cough = 'yes'; LackofAppetite = 'yes'; RunnyNose = 'yes'; SoreThroat = 'yes'; Fatigue = 'yes') -> IsMild = 1, display_mild; IsMild = 0),

    /* Determine if patient has severe COVID-19 symptoms */
    ( ( SevereCoryza = 'yes'; SevereHeadache = 'yes') -> IsSevere = 1, display_severe; IsSevere = 0),

    /* Determine if patient has GAMMA variant symptoms */
    (SevereCoryza = 'yes', SevereHeadache = 'yes' -> IsGamma = 1, display_gamma; IsGamma = 0),

    /* Determine if patient has OMNICRON variant symptoms */
    (RunnyNose = 'yes', SoreThroat = 'yes', Fatigue = 'yes' -> IsOmni = 1, display_omnicron; IsOmni = 0),
	
	/* Determine if patient has OMNICRON variant symptoms with underlying COVID-19 symptoms */ 
	((RunnyNose = 'yes' ; SoreThroat = 'yes' ; Fatigue = 'yes'; Fever = 'yes'; Cough = 'yes'; LackofAppetite = 'yes'; )-> IsUnderlying = 1, display_underlying; IsUnderlying = 0),

    /* Determine if patient has low blood pressure*/
    /* TODO: check for new low bp symptoms we added */
    ((Dizziness == 'yes'; Fainting == 'yes'; BlurredVision == yes),(Systolic < 90; Diastolic < 60) -> IsLowBP is 1; IsLowBP is 0),

    store_patient(IsMild, IsSevere, IsGamma, IsOmni, IsLowBP, CTemp),

    /*Call Patients record function to save to file*/

    save_patient_records(Name, Age, Fever, Cough, LackofAppetite, SevereCoryza, SevereHeadache, RunnyNose, SoreThroat, Fatigue, Dizziness, Fainting, BlurredVision, CTemp, Systolic, Diastolic).
	
save_symptom(Symptom, Disease):- 
	assert(symptom(Symptom,Disease)),
	nl, 
	write('Disease: '), 
	write(Disease), 
	write('Symptom: '), 
	write(Symptom). 
	
patient_infostorage(IsMild, IsSevere, IsGamma, IsOmni, IsUnderlying, IsLowBP, CTemp):- 
	(IsMild == 1 -> add_mild_count; true), 
	
	(IsSevere == 1 -> add_severe_count; true), 
	
	(IsGamma == 1 -> add_gamma_count; true), 
	
	(IsOmni == 1 -> add_omni_count; true), 
	
	(IsUnderlying == 1 -> add_under_count; true), 
	
	(IsLowBP == 1 -> add_lbp_count; true), 
	
	add_count, 
	
	 /* TODO: store mild, severe, gamma, omni, underlying omni, low blood pressure and total count */
    open('total-mild.txt', write, Out), mild_count(X), write(Out, X), close(Out),
    open('total-severe.txt', write, Out2), severe_count(X), write(Out2, X), close(Out2),
    open('total-gamma.txt', write, Out3), gamma_count(X), write(Out3, X), close(Out3),
    open('total-omni.txt', write, Out4), omni_count(X), write(Out4, X), close(Out4),
	open('total-underlying-omni.txt', write, Out5), omni_underlyingcount(X),write(Out5,X),close(Out5),
    open('total-lbp.txt', write, Out6), lowbloodpressure_count(X), write(Out6, X), close(Out6),
    open('total.txt', write, Out7), total(X), write(Out7, X), close(Out7),

    assert(disease_patient(IsMild, IsSevere, IsGamma, IsOmni, IsUnderlying, IsLowBP, CTemp)).
	
percentage_stat(Count, Total, Percentage):- 
	Percentage is (Count/ Total)* 100. 
	
	/* get_mild_count:- mild_count(X), write(X). */
	add_mild_count:- mild_count(X), NewCount is X + 1, retractall(mild_count(_)),assert(mild_count(NewCount)). 

	add_severe_count:- severe_count(X), NewCount is X + 1, retractall(severe_count(_)),assert(severe_count(NewCount)). 

	add_gamma_count:- gamma_count(X), NewCount is X + 1, retractall(gamma_count(_)),assert(gamma_count(NewCount)). 

	add_omni_count:- omni_count(X), NewCount is X + 1, retractall(omni_count(_)),assert(omni_count(NewCount)). 

	add_under_count:- omni_underlyingcount(X), NewCount is X + 1, retractall(omni_underlyingcount(_)),assert(omni_underlyingcount(NewCount)). 
	
	add_lbp_count:- lowbloodpressure_count(X), NewCount is X + 1, retractall(lowbloodpressure_count(_)),assert(lowbloodpressure_count(NewCount)). 
	add_count:- total(X), NewCount is X + 1, retractall(total(_)),assert(total(NewCount)). 
	
save_patient_records(Name, Age, Fever, Cough, LackofAppetite, SevereCoryza, SevereHeadache, RunnyNose, SoreThroat, Fatigue, Dizziness, Fainting, BlurredVision, CTemp, Systolic, Diastolic):-
    open('PatientsListing.txt', append, Out),
    write(Out, 'Name: '), write(Out, Name), nl(Out),
	write(Out, 'Age: '), write(Out, Age), nl(Out),
    write(Out, 'Fever: '), write(Out, Fever), nl(Out),
    write(Out, 'Cough: '), write(Out, Cough), nl(Out),
    write(Out, 'Tiredness: '), write(Out, Tiredness), nl(Out),
    write(Out, 'Severe Coryza: '), write(Out, SevereCoryza), nl(Out),
    write(Out, 'Severe Headache: '), write(Out, SevereHeadache), nl(Out),
    write(Out, 'Runny Nose: '), write(Out, RunnyNose), nl(Out),
    write(Out, 'Sore Throat: '), write(Out, SoreThroat), nl(Out),
    write(Out, 'Fatigue: '), write(Out, Fatigue), nl(Out),
    write(Out, 'Dizziness: '), write(Out, Dizziness), nl(Out),
    write(Out, 'Fainting: '), write(Out, Fainting), nl(Out),
    write(Out, 'Blurred Vision: '), write(Out, BlurredVision), nl(Out),
    write(Out, 'Temperature (in Celsius): '), write(Out, CTemp), nl(Out),
    write(Out, 'Systolic: '), write(Out, Systolic), nl(Out),
    write(Out, 'Diastolic: '), write(Out, Diastolic), nl(Out),
    write(Out, '======================================='), write(Out,nl), write(Out,nl),
    close(Out).


display_mild:-
     write('The patient is displaying mild COVID-19 symptoms. Proceed with the following suggestions: '), nl,
     write('1. Isolation at home is recommended, which includes rest, drinks, and over-the-counter pain medications.'), nl,
     write('2. Consume vitamin C-containing drinks and electrolyte-rich fluids.'), nl, nl.

display_severe:-
    write('The patient is exhibiting severe COVID-19 symptoms. Proceed with the following suggestions: '), nl,
    write('1. Patient should immediately get vaccinated.'), nl,
    write('2. Patient should be admitted for further PCR testing.'), nl, nl.


display_gamma:-
    write('The patient has gamma covid variant symptoms. Proceed with the following suggestions: '), nl,
    write('1. Patient should immediately get vaccinated.'), nl,
    write('2. Stay isolated from all persons for 7 days.'), nl,
    write('3. Sanitize and disinfect things or items used in the house.'), nl, nl.

display_omnicron:-
    write('The patient has omnicron covid variant symptoms. Proceed with the following suggestions: '), nl,
    write('1. Patient should immediately get vaccinated.'), nl,
    write('2. Patient should stay isolated at home for a period of 5 days.'), nl, nl.
	
display_underlying:- 
	write('The patient has omnicron variant with underlying conditions. Proceed with the following suggestions: '), nl,
    write('1. Patient should immediately get vaccinated, and follow up on booster shots.'), nl,
    write('2. Ensure to wear a mask when interacting with other people.'), nl, nl.	
%!
