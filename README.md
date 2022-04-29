1). Created a Redis Like In-memory Cache in Java.
2).Created custom LRU Map for the same by using Linked(custom) data structure, for storing the data in the cache in Key-Value pair.
3).Every activity i.e adding,removing and retrieving elements from the cache is synchronized i.e only one Thread can perform any of these operations at a time.
4).Used Daemon Thread to run the cleanp up activity in the backgroup,which will remove the elements from the cache on the basis of their last accessed time.
5).Added Test class to test the execution of all the activities.
