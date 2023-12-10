const {Client} = require('pg')

const client = new Client ({
    host: "localhost",
    user: "postgres",
    post: 5432,
    password: "123Ania123",
    database: "iodata"
})

client.connect();

client.query(`Select * from users`, (err, res) =>{
    if(!err) {
        console.log(res.rows);
    }else{
            console.log(err.message);

        }
        client.end;
})