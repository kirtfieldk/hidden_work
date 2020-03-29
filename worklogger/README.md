## Logic For Dif Between Same Files


At the moment, there are only four tables implemented with the postgres
database.
*   Insertion

*   Deletion

*   Content

*   Files

Through the client this program feeds its personal db and analyzes it 
data correctly.

Base urls:

`api/v1/insertion` 

`api/v1/deletion`

`api/v1/difference`

`api/v1/content`

`api/v1/files`

When comparing the same files at different times, the files must have 
the same file ID, but different content Ids.

To add a List of content/files use

`api/v1/content/list`

`api/v1/files/list`


After feeding the db with new files and content, invoke the
`api/v1/difference/create/<id1>/<id2>`

The ids of the files/content does not matter because the service 
auto fetches the recent file out of the two. 

To get the full difference with all the appropriate information
use:

`api/v1/differences/id/fetch/<id1>/<id2>`