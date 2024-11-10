import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Container } from 'react-bootstrap';

/**
 * Employee Component Props
 */
interface EmployeeComponentProps {
  user: {
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
export function EmployeeComponent({ user }: EmployeeComponentProps) {
  // User list
  const [userList, setUserList] = useState<User[]>([]);

  // Toggle Modal
  const [isModalOpen, setIsModalOpen] = useState(false);
  const toggleModal = () => {
    setIsModalOpen(!isModalOpen);
  };
  
  useEffect(() => {
    const getUsers = async () => {
      try {
        const response = await axios.get("http://localhost:150/users/all");
        setUserList(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    getUsers();
  })
  
  // HTML
  return (
    <div>
      {userList.map((currentUser) => (
        <Container key={currentUser.username} className='bg-secondary mt-4'> 
          <div>
            First Name: {currentUser.firstName}
          </div>
          <div>
            Last Name: {currentUser.lastName}
          </div>
          <div>
            Role: {currentUser.role}
          </div>

          {/* Modal Button */}
          <button type="button" className="btn btn-primary" onClick={toggleModal}>
            Edit
          </button>
          
          {/* Modal */}
          {isModalOpen && (
            <div className="modal fade show" style={{ display: 'block' }} tabIndex={-1} role="dialog" aria-labelledby="userModalTitle" aria-hidden="true">
              <div className="modal-dialog modal-dialog-centered" role="document">
                <div className="modal-content">
                  {/* Modal Header */}
                  <div className="modal-header">
                    <h5 className="modal-title text-dark" id="userModalTitle">Editting User</h5>
                  </div>
                  {/* Modal Content */}
                  <div className="modal-body text-dark">
                    TO DO: Insert A Form Here
                  </div>
                  {/* Modal Footer */}
                  <div className="modal-footer">
                    <button type="button" className="btn btn-secondary" onClick={toggleModal}>Close</button>
                    <button type="button" className="btn btn-primary">Save</button>
                  </div>
                </div>
              </div>
            </div>
          )}
        </Container>
      ))}
    </div>
  )
}