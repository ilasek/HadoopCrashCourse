var fs = require("fs");
var pg = require('pg');

var conString = "postgres://hadoopdemo:hadoopdemo@localhost/hadoopdemo";

var inserted = 0;

//this initializes a connection pool
//it will keep idle connections open for a (configurable) 30 seconds
//and set a limit of 20 (also configurable)
pg.connect(conString, function(err, client, done) {
    if(err) {
        return console.error('error fetching client from pool', err);
    }

    var file = fs.readFileSync("yeast_abbrev_labeled.txt").toString().split("\r\n\r\n\r\n\r\n");
    for (var i in file) {
        file[i] = file[i].split("\r\n\r\n");


        client.query('INSERT into pubmed_texts (source, title, authors, institution, abstract, pmid) VALUES($1, $2, $3, $4, $5, $6) RETURNING pmid', 
                file[i], function(err, result) 
            {
            //call `done()` to release the client back to the pool
            done();

            if(err) {
              return console.error('error running query', err);
            }
            console.log("Inserted " + ++i);
            //output: 1
        });

        // for (var j in file[i]) {
        //     console.log(file[i][j]);
        // }
        console.log("=====================");
    }
    console.log(file.length);
});