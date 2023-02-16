import logo from "./repair-image.svg";
import "./LandingPage.css";
import Button from "@mui/material/Button";
import { TextField } from "@mui/material";
import { useNavigate } from "react-router-dom";
function LandingPage() {
  const navigate = useNavigate();
  return (
    <div>
      {" "}
      <div className="app">
        <div className="insider">
          <img src={logo} alt="logo" />
          <div className="promotion">
            <div className="CLOCKIT">
              <div className="head">
                <p id="clock" className="clockit">
                  Ley
                </p>
                <p id="it" className="clockit">
                  Fit
                </p>
              </div>

              <div className="motto">
                <p id="motto">
                  Ashok Leyland's own spare part management system!{" "}
                </p>
                <p id="motto2">
                  Leyfit allows you to manage all inventory items <br />{" "}
                  conveniently and get it delivered in time. <br />{" "}
                </p>
                <br />
                <div style={{ textAlign: "center", margin: "1rem" }}>
                  <TextField
                    id="standard-basic"
                    label="Garage Code"
                    variant="standard"
                  />
                </div>
                <div style={{ textAlign: "center", margin: "1rem" }}>
                  <TextField
                    id="standard-basic"
                    label="Password"
                    variant="standard"
                  />
                </div>
                <div style={{ textAlign: "center", margin: "1rem" }}>
                  <Button
                    style={{ width: "50%" }}
                    variant="contained"
                    onClick={() => {
                      navigate("/dashboard");
                    }}
                  >
                    Login
                  </Button>
                </div>
              </div>
            </div>
          </div>

          <div className="dummy"></div>
        </div>

        <div className="footer-holder">
          <footer class="footer" id="footer">
            <p>Made with ♥️ by CodeRizz</p>

            <p class="footer-copyright">&copy; All Rights Reserved.</p>
          </footer>
        </div>
      </div>
    </div>
  );
}

export default LandingPage;
