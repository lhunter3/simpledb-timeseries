# Timeseries Datatype and Querying within Simple DB

All functionality can be executed directly with the SQLInterprator. No driver program is required.

## Supported Functionality & Example Queries

- **Create table with timeseries datatype:**   ```create table name(id int, t timeseries);```
- **Insert Data:** ```insert into name(t) values(20);```  where the timeseries data value is an integer.
- **Selecting Data:** ```select id,t from name;```,
- **Selecting Date while using Predicates:** ```select id,t from name where t=20``` OR ```select id,t from name where t='2023-03-02-20'```
- **Aggregate Functions:** ```select sum(t) from name``` also works with predicates like ```select sum(t) from name where t=20``` or  ```select sum(t) from name where t='2023-03-02-20'``` but will not work  while selecting multiple fields ie:  ```select id,sum(t) from name```


## Changes can be found at: 
- `Lexer.java`
- `Parser.java`
- `SQLInterpretor.java`
- `TableScan.java`
- `Buffer.java`
- `Scan.java`
- `RecordFile.java`
- `RecordPage.java`
- `Term.java`
- `Transaction.java`
- `Page.java`
- `TimeseriesConstant.java`
- `RemoteMetaDataImpl.java`
- `RemoteResultSetImpl.java`

## Individual Contribution
We collaborated equally on most tasks, with each team member contributing to various aspects of the project. Specifically, Lucas took the lead in implementing the querying and Aggregate Scan. On the other hand, Jacob played a crucial role in researching the correct implementation when using a Time Reference System.

The remaining programming aspects of the project were addressed together through pair programming sessions. We organized a few meetings to tackle the necessary steps for implementing the working datatype.


## Known Issues

Occasionally when creating a table with multiple datatypes and a timeseries datatype where the field name is larger than 4, the table behaviours becomes unpredictable. The values get inserted correctly, but then get lost when attempting to retreive them. See image for details.

![image](https://github.com/CS3543-Project/simpledb/assets/80433013/bed09f64-d8b7-4a7f-9da6-9e5d27435edf)

Notice that the id value '1' is not getting retreived properly.



 
