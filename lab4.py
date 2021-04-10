from mimesis import Person
from mimesis import Address
from mimesis.enums import Gender
import random
import sys
import pandas as pd
import csv
import argparse
parser = argparse.ArgumentParser()
parser.add_argument("a", default=10, type=int, help="Count of lines")
parser.add_argument("str",default="en_US", type=str, help="Location")
args = parser.parse_args()
country=[]
location=args.str
if location=="en_US":
    country.append("US")
    country.append("United States")
    location='en'
elif location=="ru_RU":
    country.append("RU")
    country.append("Russia")
    location='ru'
elif location=="uk_UA":
    country.append("UA")
    country.append("Ukraine")
    location='uk'
elif location=="be_BY":
    country.append("BY")
    country.append("Belarus")
    location='be'
else:
    print("Wrong location data")
    sys.exit()
if args.a<1:
    print("Wrong location data")
    sys.exit()
adres=Address(location)
person = Person(location) 
datamas=[]
for _ in range(0, args.a):
    cell=[]
    cell.append(person.full_name())
    cell.append(person.telephone())
    cell.append(random.choice(country))
    cell.append(adres.city())
    cell.append(adres.zip_code())
    cell.append(adres.address())
    datamas.append(cell)
df=pd.DataFrame(datamas)
df.to_csv("nul",';',index=False)