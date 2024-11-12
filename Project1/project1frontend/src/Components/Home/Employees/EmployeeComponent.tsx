import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Button, Container, Form, Modal, ModalDialog } from 'react-bootstrap';
import './employee.css'
import { useAuthentication } from '../../../Context/AuthenticationContext';
import { useNavigate } from 'react-router-dom';

/**
 * Employee Component Props
 */
interface EmployeeComponentProps {
  AuthenticatedUser: {
    firstName: string;
    lastName: string;
    username: string;
    role: string;
  };
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
 * Employee Component
 * @returns HTML
 */
export function EmployeeComponent({ AuthenticatedUser }: EmployeeComponentProps) {  
  // User list
  const [userList, setUserList] = useState<User[]>([]);

  // Toggle modal
  const [isModalOpen, setIsModalOpen] = useState(false);
  const toggleModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  // Selected user
  const [selectedUser, setSelectedUser] = useState({
    firstName: '',
    lastName: '',
    username: '',
    role: '',
  });

  // Handles checkbox changes
  const handleCheckboxChange = (event: { target: { value: string; }; }) => {
    selectedUser.role = event.target.value;
  }

  // Retrieve users
  useEffect(() => {
    const getUsers = async () => {
      try {
        const response = await axios.get('http://localhost:150/users/all');
        setUserList(response.data);
      } catch (error) {
        console.log('Failed to retrieve all users: ', error);
      }
    };
    getUsers();
  })

  // Update role
  const [isUpdated, setIsUpdated] = useState(false);
  const updateRole = async () => {
    try {
      await axios.put('http://localhost:150/users/update', selectedUser);
      setIsUpdated(true);
    } catch (error) {
      console.log('Failed to update role: ', error);
    }
  };
  
  // HTML
  return (
    <div className='d-flex flex-wrap mt-4'>
      {/* User List */}
      {userList.map((currentUser) => (
        <div key={currentUser.username} className='bg-secondary m-3 p-3 d-flex flex-column custom-cursor custom-container' data-toggle='modal' onClick={() => {toggleModal(); setSelectedUser(currentUser); setIsUpdated(false)}}> 
          <div className='d-flex'>
            <h5 className='custom-width'>First Name |</h5>
            <h5 className='custom-font'>{currentUser.firstName}</h5>
          </div>
          <div className='d-flex'>
            <h5 className='custom-width'>Last Name |</h5>
            <h5 className='custom-font'>{currentUser.lastName}</h5>
          </div>
          <div className='d-flex'>
            <h5 className='custom-width'>Username |</h5>
            <h5 className='custom-font'>{currentUser.username}</h5>
          </div>
          <div className='d-flex'>
            <h5 className='custom-width'>Role |</h5>
            <h5 className='custom-font'>{currentUser.role}</h5>
          </div>
        </div>
      ))}

      {/* Modal */}
      <Modal show={isModalOpen} onHide={toggleModal} backdrop='static' keyboard={false} animation={true}>
        <Modal.Header closeButton>
          <Modal.Title>Update Role for {selectedUser.firstName} {selectedUser.lastName}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form className='d-flex flex-row justify-content-center'>
            <section>
              <Form.Check className='m-3' type='radio' name='role' value='User' id='userRole' label='User' checked={selectedUser.role === 'User'} onChange={handleCheckboxChange}/>
            </section>
            {(AuthenticatedUser.role === 'Employee' || AuthenticatedUser.role === 'Manager')&& 
              <div>
                <Form.Check className='m-3' type='radio' name='role' value='Employee' id='employeeRole' label='Employee' checked={selectedUser.role === 'Employee'} onChange={handleCheckboxChange}/> 
              </div>
            }
            {AuthenticatedUser.role === 'Manager' && 
              <div>
                <Form.Check className='m-3' type='radio' name='role' value='Manager' id='managerRole' label='Manager' checked={selectedUser.role === 'Manager'} onChange={handleCheckboxChange}/>
              </div>
            }
          </Form>
        </Modal.Body>
        <Modal.Footer>
          {isUpdated && <div className='text-success'>Successfully Updated Role</div>}
          <Button variant='success' onClick={updateRole}>Save</Button>
        </Modal.Footer>
      </Modal>
    </div>
  )
}