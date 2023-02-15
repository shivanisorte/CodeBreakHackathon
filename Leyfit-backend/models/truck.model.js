const mongoose = require("mongoose");
const { Schema } = mongoose;

const TruckSchema = new Schema({
  truckNumner: {
    type: String,
    unique: true,
    required: [true, "truck number is required "],
  },
  password: {
    type: String,
    required: [true, "password is required "],
  },
});

const Truck = mongoose.model("Truck", TruckSchema);

module.exports = { Truck };
