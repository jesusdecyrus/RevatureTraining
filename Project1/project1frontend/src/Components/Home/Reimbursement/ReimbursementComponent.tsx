import axios from "axios";
import { SetStateAction, useEffect, useState } from "react";
import { Button, Dropdown, DropdownButton, Form, Modal } from "react-bootstrap";
import "./reimbursement.css"
import { useAuthentication } from "../../../Context/AuthenticationContext";

/**
 * ReimbursementComponent Props
 */
interface ReimbursementComponentProps {
  AuthenticatedUser: {
    firstName: string;
    lastName: string;
    username: string;
    role: string;
  };
}

/**
 * Reimbursement Object
 */
interface Reimbursement {
  reimbursementId: number;
  description: string;
  amount: number;
  status: string;
  username: string;
}

/**
 * User Object
 */
interface User {
  firstName: string;
  lastName: string;
  username: string;
  role: string;
}

/**
 * ReimbursementComponent Component
 * @returns HTML
 */
export function ReimbursementComponent({ AuthenticatedUser }: ReimbursementComponentProps) {
  // User Id
  const [userId, setUserId] = useState(0);
  const { AuthenticationData } = useAuthentication();
  const getUserId = async () => {
    try {
      const response = await axios.get(`http://localhost:150/users/id/${AuthenticationData.username}`);
      setUserId(response.data);
    } catch (error) {
      console.log("Failed to get user id: ", error);
    }
  };
  getUserId();
  
  // Load ALL reimbursements when component mounts
  const [allReimbursements, setAllReimbursements] = useState<Reimbursement[]>([]);
  useEffect(() => {
    if (AuthenticatedUser.role === "Manager") {
      getAllReimbursements();
    }
  }, [AuthenticatedUser])
  // Backend communication
  const getAllReimbursements = async () => {
    try {
      const response = await axios.get("http://localhost:150/reimbursements/all");
      setAllReimbursements(response.data)
    } catch (error) {
      console.log("Failed to get all reimbursements: ", error);
    }
  }

  // Load ALL users when component mounts
  const [allUsers, setAllUsers] = useState<User[]>([]);
  useEffect(() => {
    if (AuthenticatedUser.role === "Manager") {
      getAllUsers();
    }
  }, [AuthenticatedUser])
  // Backend communication
  const getAllUsers = async () => {
    try {
      const response = await axios.get("http://localhost:150/users/all");
      setAllUsers(response.data)
    } catch (error) {
      console.log("Failed to get all users: ", error);
    }
  }

  // Load OWNED reimbursements when component mounts
  const [ownedReimbursements, setOwnedReimbursements] = useState<Reimbursement[]>([]);
  useEffect(() => {
    if (AuthenticatedUser.role === "Employee" || AuthenticatedUser.role === "Manager") {
      getOwnedReimbursements();
    }
  }, [AuthenticatedUser])
  // Backend communication
  const getOwnedReimbursements = async () => {
    try {
      const response = await axios.get(`http://localhost:150/reimbursements/owned/${userId}`);
      setOwnedReimbursements(response.data)
    } catch (error) {
      console.log("Failed to get owned reimbursements: ", error);
    }
  }

  // Reimbursement Toggle
  const [isAllReimbursement, setIsAllReimbursements] = useState(false);

  // Reimbursement Edit Modal
  const [isModalOpen, setIsModalOpen] = useState(false);
  const toggleModal = () => {
    setIsModalOpen(!isModalOpen);
    setIsUpdated(false);
  };
  const handleClick = (reimbursement: Reimbursement) => {
    setSelectedReimbursement(reimbursement);
    setStatus(reimbursement.status);
  };
  // Status Update
  const [status, setStatus] = useState("");
  const [selectedReimbursement, setSelectedReimbursement] = useState<Reimbursement>();
  const [isUpdated, setIsUpdated] = useState(false);
  const handleCheckboxChange = (event: { target: { value: string; }; }) => {
    setStatus(event.target.value);
  }
  const updateStatus = async () => {
    try {
      await axios.put(`http://localhost:150/reimbursements/update/${selectedReimbursement?.reimbursementId}/${status}`);
      setIsUpdated(true);
    } catch (error) {
      console.log('Failed to update status: ', error);
    }
  };

  // Request Reimbursement Modal
  const [isRequestModalOpen, setIsRequestModalOpen] = useState(false);
  const [isRequested, setIsRequested] = useState(false);
  const [isRequestedError, setIsRequestedError] = useState(false);
  const toggleRequestModal = () => {
    setIsRequestModalOpen(!isRequestModalOpen);
    setIsRequested(false);
    setIsRequestedError(false);
  };
  // Request Reimbursement
  const [requestReimbursement, setRequestReimbursement] = useState({
    description: "",
    amount: 0,
    status: "Pending",
    username: AuthenticationData.username,
  });
  const handleReimbursementChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setRequestReimbursement((previousReimbursement) => ({ ...previousReimbursement, [name]: value }));
  };
  const sendRequestReimbursement = async () => {
    console.log(requestReimbursement);
    try {
      await axios.post("http://localhost:150/reimbursements", requestReimbursement);
      setIsRequestedError(false);
      setIsRequested(true);
      setRequestReimbursement({
        description: "",
        amount: 0,
        status: "Pending",
        username: AuthenticationData.username,
      });
    } catch (error) {
      setIsRequestedError(true);
      setIsRequested(false);
      console.log('Failed to send reimbursement: ', error);
    }
  };

  // Status Filter
  const [statusFilter, setStatusFilter] = useState("");

  // HTML
  return (
    <div>
      {/* Welcome Message */}
      <section className="d-flex flex-row mt-4 mb-4 bbw">
        <h4>Welcome, {AuthenticatedUser.firstName} {AuthenticatedUser.lastName}!</h4>
      </section>
      <h5 className="d-flex flex-row">Reimbursements</h5>

      {/* Reimbursement Buttons */}
      <section className="d-flex flex-row">
        {AuthenticatedUser.role === "Manager" &&
          <div>
            <button className={`btn m-1 
              ${isAllReimbursement ? 'bg-secondary' : 'btn-outline-secondary'}`} onClick={() => setIsAllReimbursements(true)}>All</button>
          </div>
        }
        <div>
          <button className={`btn m-1 
            ${!isAllReimbursement ? 'bg-secondary' : 'btn-outline-secondary'}`} onClick={() => setIsAllReimbursements(false)}>Owned</button>
        </div>
        <div>
          <Dropdown>
            <DropdownButton className="m-1" variant="primary" id="dropdown-basic-button" title="Filter">
            <Dropdown.Item href="#/all" onClick={() => setStatusFilter("")}>All</Dropdown.Item>
              <Dropdown.Item href="#/pending" onClick={() => setStatusFilter("Pending")}>Pending</Dropdown.Item>
              <Dropdown.Item href="#/approved" onClick={() => setStatusFilter("Approved")}>Approved</Dropdown.Item>
              <Dropdown.Item href="#/denied" onClick={() => setStatusFilter("Denied")}>Denied</Dropdown.Item>
            </DropdownButton>
          </Dropdown>
        </div>
        <div>
          <button className="btn m-1 btn-success" onClick={toggleRequestModal}>Request Reimbursement</button>
        </div>
      </section>

      {/* GRID TITLES */}
      <article className="container mt-3 mb-3">
        <div className="row grid-titles">
          <div className="col-sm-2 left">Owner</div>
          <div className="col-sm-6 left">Description</div>
          <div className="col-sm-2">Amount</div>
          <div className="col-sm-2">Status</div>
        </div>
      </article>
      {/* MAP EACH REIMBURSEMENTS INTO GRID */}
      <section className="mt-2">
        {isAllReimbursement ? 
          // ALL
          allReimbursements
            .filter((currentReimbursement) => 
              statusFilter ? currentReimbursement.status === statusFilter : true
            )
            .map((currentReimbursement) => {
              const ownerUser = allUsers.find(currentUser => currentUser.username === currentReimbursement.username);
              return (
                // A User's Reimbursement
                <article className="container bg-dark mb-2 custom-round">
                  <div className="row custom-select" onClick={() => {toggleModal(); handleClick(currentReimbursement);}}>
                    <div className="col-sm-2 p-2 text-dark bg-light bold custom-round left">
                      {ownerUser ? `${ownerUser.firstName} ${ownerUser.lastName}` : "User not found"}
                    </div>
                    <div className="col-sm-6 p-2 left bg-secondary custom-round">
                      {currentReimbursement.description}
                    </div>
                    <div className="col-sm-2 p-2 bg-dark custom-round">
                      ${currentReimbursement.amount.toLocaleString("en-US")}
                    </div>
                    <div className={`col-sm-2 p-2 text-dark custom-round
                      ${currentReimbursement.status === 'Pending' ? 'bg-warning' : ''} 
                      ${currentReimbursement.status === 'Denied' ? 'bg-danger' : ''} 
                      ${currentReimbursement.status === 'Approved' ? 'bg-success' : ''}`}>
                      {currentReimbursement.status}
                    </div>
                  </div>
                </article>
              )}) :
          // OWNED
          ownedReimbursements
            .filter((currentReimbursement) => 
              statusFilter ? currentReimbursement.status === statusFilter : true
            )
            .map(currentOwnedReimbursement => (
              <article className="container bg-dark mb-2 custom-round">
                <div className="row custom-select" onClick={() => {toggleModal(); handleClick(currentOwnedReimbursement);}}>
                  <div className="col-sm-2 p-2 text-dark bg-light bold custom-round left">
                    {AuthenticatedUser.username}
                  </div>
                  <div className="col-sm-6 p-2 left bg-secondary custom-round">
                    {currentOwnedReimbursement.description}
                  </div>
                  <div className="col-sm-2 p-2 bg-dark custom-round">
                    ${currentOwnedReimbursement.amount.toLocaleString("en-US")}
                  </div>
                  <div className={`col-sm-2 p-2 text-dark custom-round
                    ${currentOwnedReimbursement.status === 'Pending' ? 'bg-warning' : ''} 
                    ${currentOwnedReimbursement.status === 'Denied' ? 'bg-danger' : ''} 
                    ${currentOwnedReimbursement.status === 'Approved' ? 'bg-success' : ''}`}>
                    {currentOwnedReimbursement.status}
                  </div>
                </div>
              </article>
          ))
        }
      </section>

      {/* Edit Modal */}
      <Modal show={isModalOpen} onHide={toggleModal} backdrop='static' keyboard={false} animation={true}>
        <Modal.Header closeButton>
          <Modal.Title>Update Status</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form className='d-flex flex-row justify-content-center'>
            <section>
              <Form.Check className='m-3' type='radio' name='role' value='Pending' id='pending' label='Pending' checked={status === 'Pending'} onChange={handleCheckboxChange}/>
            </section>
            {(AuthenticatedUser.role === 'Employee' || AuthenticatedUser.role === 'Manager')&& 
              <div>
                <Form.Check className='m-3' type='radio' name='role' value='Approved' id='approved' label='Approved' checked={status === 'Approved'} onChange={handleCheckboxChange}/> 
              </div>
            }
            {AuthenticatedUser.role === 'Manager' && 
              <div>
                <Form.Check className='m-3' type='radio' name='role' value='Denied' id='denied' label='Denied' checked={status === 'Denied'} onChange={handleCheckboxChange}/>
              </div>
            }
          </Form>
        </Modal.Body>
        <Modal.Footer>
          {isUpdated && <div className='text-success'>Successfully Updated Status</div>}
          <Button variant='success' onClick={updateStatus}>Save</Button>
        </Modal.Footer>
      </Modal>

      {/* Request Modal */}
      <Modal show={isRequestModalOpen} onHide={toggleRequestModal} backdrop='static' keyboard={false} animation={true}>
        <Modal.Header closeButton>
          <Modal.Title>Request Reimbursement</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className="d-flex">
            <h5 className="mt-1 custom-profile1">Description |</h5>
            <a>
              <Form.Control
                type="text"
                placeholder="enter description"
                name="description"
                value={requestReimbursement?.description}
                onChange={handleReimbursementChange}
                className="custom-form"
              />
            </a>
          </div>
          <div className="d-flex">
            <h5 className="mt-1 custom-profile1">Amount |</h5>
            <a>
              <Form.Control
                type="number"
                placeholder="enter amount"
                name="amount"
                value={requestReimbursement?.amount}
                onChange={handleReimbursementChange}
                className="custom-form"
              />
            </a>
          </div>
        </Modal.Body>
        <Modal.Footer>
          {isRequested && <div className='text-success'>Successfully Requested Reimbursement</div>}
          {isRequestedError && <div className='text-danger'>Enter Valid Fields</div>}
          <Button variant='success' onClick={sendRequestReimbursement}>Request</Button>
        </Modal.Footer>
      </Modal>
    </div>
  )
}