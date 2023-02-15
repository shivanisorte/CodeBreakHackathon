const express = require("express");
const router = express.Router();
const { Truck } = require("../models/truck.model");
router.post("/login", async (req, res) => {
  try {
    const { truckNumber, password } = req.body;
    const getTruck = await Truck.findOne({ truckId: truckNumber });
    console.log(getTruck);
    if (getTruck != null && getTruck.password === password) {
      return res.status(200).json({ truck: getTruck });
    }
    return res.status(403).json({ message: "authentication error" });
  } catch (error) {
    return res.status(500).json({ success: false });
  }
});

module.exports = router;
