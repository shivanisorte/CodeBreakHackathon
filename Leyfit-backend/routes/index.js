const express = require("express");
const router = express.Router();

router.get("/", (req, res) => {
  // res.send("OUUUUUU")
  res.json({name:"Shivani"});
});

module.exports = router;
