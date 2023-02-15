const express = require("express");
const app = express();
const port = 3000;
const bodyParser = require("body-parser");
const { initializeDBConnection } = require("./db/db.connect");

initializeDBConnection();
app.use(bodyParser.json());

const indexRouter = require("./routes/index");
const trucksRouter = require("./routes/trucks");

app.use("/", indexRouter);
app.use("/truck", trucksRouter);

app.listen(port, () => {
  console.log(`Server started at http://localhost:${port}`);
});
