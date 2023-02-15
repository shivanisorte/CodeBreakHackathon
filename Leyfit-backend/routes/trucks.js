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

router.post("/register", async (req, res) => {
  try {
    const { truckNumber, purchaseDate, type } = req.body;
    const password = truckNumber.substring(truckNumber.length - 4);
    console.log(purchaseDate);
    const expiryDate = new Date(purchaseDate);
    const expiryDateString = expiryDate.toString();
    expiryDate.setMonth(expiryDate.getMonth() + 6);
    const renew = {
      lastUpdated: purchaseDate,
      expiry: expiryDate.toString(),
    };
    const truck = {
      truckNumber,
      password: password,
      brakes: renew,
      lights: renew,
      wheels: renew,
      engineOil: renew,
      wipers: renew,
      generalMaintenance: renew,
      puc: renew,
    };
    const NewTruck = new Truck(truck);
    await NewTruck.save();
    return res.status(200).json({ success: true, truck });
  } catch (err) {
    console.log(err);
    return res.status(500).json({ success: false });
  }
});

module.exports = router;
