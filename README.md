## Bitbucket Postgres Database API
ATM There are only four tables 
  1) project
  2) files
  3) repositories
  4) commits

#### Project Endpoint
`api/v1/project`

returns 
```json
[
 { 
      "id": 1,
      "description": "description",
      "project": "project"
  }
]
```

#### Repository Endpoints
`api/v1/repositories`

`api/v1/repository/id/<id>`

`api/v1/repository/name/<name>`

returns
```json

[
  {
    "repository_id": 1,
    "project_id": 1,
    "branches": "master, dev, keith_dev",
    "repository": "repository",
    "clone_url": "http://"
  }

]
```

#### Files Endpoint
`api/v1/files`

`api/v1/files/name/<name>`

`api/v1/files/id/<id>`

`api/v1/files/project/name/<name>`

`api/v1/files/project/id/<id>`

```json
[
  {
    "file_id": "A",
    "file_name": "File name",
    "repository": "DS UTILES",
    "branch": "master",
    "file_content": "Contents",
    "project_id": 1,
    "repository_id": 2
  }
]
```
