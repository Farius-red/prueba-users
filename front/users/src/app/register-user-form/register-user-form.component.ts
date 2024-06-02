import { CoreModule } from './../core/core.module';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { UsuarioService } from '../core/servicios/usuario.service';
import { RegisterUserDTO } from 'juliaositembackenexpress/src/api/dtos/RegisterUserDTO';
import { DatesUserDTO } from 'juliaositembackenexpress/src/api/dtos/DatesUserDTO';
import { PhoneDTO } from 'juliaositembackenexpress/src/api/dtos/PhoneDTO';

@Component({
  selector: 'app-register-user-form',
  templateUrl: './register-user-form.component.html',
  styleUrls: ['./register-user-form.component.scss'],
  standalone: true,
  imports: [CoreModule],
})
export class RegisterUserFormComponent implements OnInit {
  registerForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    public dialogRef: MatDialogRef<RegisterUserFormComponent>
  ) {
    this.registerForm = this.fb.group({
      firstName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern('[0-9]{10}')]],
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {

    if (this.registerForm.valid) {
      let phone :PhoneDTO ={
        number:  this.registerForm.get("phone")?.value,
        cityCode: 601,
        countryCode: 57,
        nameCity: "Bogota",
        nameCountry: 'Colombia'
      }

      let  datesUser :DatesUserDTO = {
        idDatesUser: '',
        firstName: this.registerForm.get("firstName")?.value,
        secondName: '',
        phone: [],
        idUrl: '',
        addresses: [],
        estado: '3fa85f64-5717-4562-b3fc-2c963f66afa6',
        nombreRol: ''
      } 
      datesUser.phone.push(phone);
      
      let newUser: RegisterUserDTO = {
        idBussines: 1,
        id: "",
        email:  this.registerForm.get("email")?.value,
        password: "",
        datesUser: datesUser,
        token: ""
      }
  

      this.usuarioService.add(newUser).subscribe({
          next :  (response) => {
            alert(response.message)
           this.dialogRef.close(true);
    
           },
          error: (error) => {
             alert(" salio mal al crear user");
           console.error('Error creating user', error);
          
          }
       });
    }
  }
}
