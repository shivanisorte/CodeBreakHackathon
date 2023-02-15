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
    required: true,
  },
  lights: {
    type: Map,
    required: true,
  },
  wheels: {
    type: Map,
    required: true,
  },
  engineOil: {
    type: Map,
    required: true,
  },
  wipers: {
    type: Map,
    required: true,
  },
  generalMaintenance: {
    type: Map,
    required: true,
  },
  puc: {
    type: Map,
    required: true,
  },
});

const Truck = mongoose.model("Truck", TruckSchema);

module.exports = { Truck };
