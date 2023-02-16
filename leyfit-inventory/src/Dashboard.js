import { useState } from "react";
import "./Dashboard.css";
import SideMenu from "./SideMenu";
import Button from "@mui/material/Button";
// import Modal from "./Modal";
import FormControl from "@mui/joy/FormControl";
import FormLabel from "@mui/joy/FormLabel";
import Input from "@mui/joy/Input";
import ModalDialog from "@mui/joy/ModalDialog";
import Stack from "@mui/joy/Stack";
import Modal from "@mui/joy/Modal";
// import Add from "@mui/icons-material/Add";
import Typography from "@mui/joy/Typography";
const locations = [
  { place: "Loni", distance: "5km" },
  { place: "Aundh", distance: "7km" },
  { place: "VimanNager", distance: "21km" },
  { place: "Lonavala", distance: "50km" },
  { place: "Kharghar", distance: "90km" },
];
function Dashboard() {
  const [cat, setCat] = useState("Brakes");
  const [open, setOpen] = useState(false);
  const [data, setData] = useState({
    Brakes: 5,
    Wheels: 15,
    EngineOil: 6,
    Wipers: 20,
  });
  return (
    <div className="wrapper">
      <div className="nav">
        <div className="brand">
          <h1 style={{ color: "#1677d4" }}>Leyfit</h1>
        </div>
        <div className="spares">
          {" "}
          <div
            className="spare"
            onClick={() => {
              setCat("Brakes");
            }}
          >
            <h3>Brakes</h3>
          </div>
          <div
            className="spare"
            onClick={() => {
              setCat("Wheels");
            }}
          >
            <h3>Wheels</h3>
          </div>
          <div
            className="spare"
            onClick={() => {
              setCat("EngineOil");
            }}
          >
            <h3>Engine Oil</h3>
          </div>
          <div
            className="spare"
            onClick={() => {
              setCat("Wipers");
            }}
          >
            <h3>Wipers</h3>
          </div>
        </div>
      </div>
      <div className="main">
        <h1 style={{ fontFamily: "monospace" }}>{cat}</h1>
        <Button
          variant="outlined"
          onClick={() => {
            setOpen(true);
            console.log("hjello");
          }}
        >
          Add to inventory
        </Button>
        <Modal open={open} onClose={() => setOpen(false)}>
          <ModalDialog
            aria-labelledby="basic-modal-dialog-title"
            aria-describedby="basic-modal-dialog-description"
            sx={{ maxWidth: 500 }}
          >
            <Typography id="basic-modal-dialog-title" component="h2">
              Create new project
            </Typography>
            <Typography
              id="basic-modal-dialog-description"
              textColor="text.tertiary"
            >
              Fill in the information of the project.
            </Typography>
            <form
              onSubmit={(event) => {
                event.preventDefault();
                setOpen(false);
              }}
            >
              <Stack spacing={2}>
                <FormControl>
                  <FormLabel>Model</FormLabel>
                  <Input autoFocus required />
                </FormControl>
                <FormControl>
                  <FormLabel>Quanity</FormLabel>
                  <Input required />
                </FormControl>
                <Button
                  onClick={() => {
                    if (cat === "Brakes") {
                      setData({ ...data, Brakes: data.Brakes + 1 });
                    } else if (cat === "Wheels") {
                      setData({ ...data, Wheels: data.Wheels + 1 });
                    } else if (cat === "EngineOil") {
                      setData({ ...data, EngineOil: data.EngineOil + 1 });
                    } else if (cat === "Wipers") {
                      setData({ ...data, Wipers: data.Wipers + 1 });
                    }
                    setOpen(false);
                  }}
                  //   type="submit"
                >
                  Submit
                </Button>
              </Stack>
            </form>
          </ModalDialog>
        </Modal>
        <h2>Quanity Remaining: {data[cat]}</h2>
        <h1>Requests:</h1>
        {locations.map((location) => {
          return (
            <div
              style={{
                margin: "1rem auto",
                boxShadow: "0px 2px 3px 0px",
                padding: "1rem",
                width: "50%",
              }}
            >
              <div style={{ display: "flex", justifyContent: "space-between" }}>
                <div>
                  <div style={{ textAlign: "left" }}> {location.place}</div>
                  <div style={{ textAlign: "left" }}>
                    distance: {location.distance}
                  </div>
                </div>
                <div>
                  <Button>Dispatch</Button>
                </div>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}
export default Dashboard;
