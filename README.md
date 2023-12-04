# Timeseries Datatype and Queries withing Simple DB

All functionality can be executed directly with the SQLInterprator. No driver program is required.

## Supported Functionality & Example Queries

- #### Create table schema with a timeseries datatype
  ```create table name(id int, t timeseries);```

- #### Insert Data
  ```insert into name(id,t) values(1,20);```

- #### Selecting Data
  ```select id,t from name;```

  using timeseries integer value as predicate 

  ```select id,t from name where t=20```

  using timeseries timetick value as predicate

  ```select id,t from name where t='2023-03-02-20'```


- #### Aggregate Functions
  ```select sum(t) from name```

  works with predicates

  ```select sum(t) from name where t=20```

  ```select sum(t) from name where t='2023-03-02-20'```

  will not work while selecting multiple fields

  ```select id,sum(t) from name```


## Known Issues

Occasionally when creating a table with multiple datatypes and a timeseries datatype where the field name is larger than 4, the table behaviours becomes unpredictable. The values get inserted correctly, but then get lost when attempting to retreive them. See image for details.


![image](https://github.com/CS3543-Project/simpledb/assets/80433013/bed09f64-d8b7-4a7f-9da6-9e5d27435edf)



 
