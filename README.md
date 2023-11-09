# simpledb


## getTimeseries() // setTimeseries() methods in these classes require attention. 

Currently treated exact same as integer. 
They should  be modified in a way that the db can store the timestamp and value not as string (so that querying works)
possibly a custom object, possibly tuple. Up for discussion.


```
\buffer\Buffer.java
\file\Page.java

\index\query\IndexJoinScan.java
\index\query\IndexSelectScan.java

\materialize\GroupByScan.java
\materialize\MergeJoinScan.java
\materialize\SortScan.java

\metadata\IndexInfo.java

\multibuffer\ChunkScan.java
\multibuffer\MultiBufferProductScan.java

\query - a timeseries constant must be created.
\query\ProductScan.java
\query\ProjectScan.java
\query\Scan.java
\query\SelectScan.java
\query\TableScan.java
\query\UpdateScan.java

\record\RecordFile.java
\record\RecordPage.java
\record\Schema.java

\tx\recovery\RecoveryMgr.java
\tx\Transaction.java

\opt\HeuristicQueryPlanner.java

``````