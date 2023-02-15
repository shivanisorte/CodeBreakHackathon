const mongoose = require("mongoose");
const { Schema } = mongoose;

const Renew = new Schema({
  lastUpdated: String,
  expiry: String,
});

const TruckSchema = new Schema({
  truckNumber: {
    type: String,
    unique: true,
    required: [true, "truck number is required "],
  },
  password: {
    type: String,
    required: [true, "password is required "],
  },
  brakes: {
    type: Map,
    // of: Renew,
    required: true,
  },
  lights: {
    type: Map,
    // of: Renew,
    required: true,
  },
  wheels: {
    type: Map,
    // of: Renew,
    required: true,
  },
  engineOil: {
    type: Map,
    // of: Renew,
    required: true,
  },
  wipers: {
    type: Map,
    // of: Renew,
    required: true,
  },
  generalMaintenance: {
    type: Map,
    // of: Renew,
    required: true,
  },
  puc: {
    type: Map,
    // of: Renew,
    required: true,
  },
});

const Truck = mongoose.model("Truck", TruckSchema);

module.exports = { Truck };
