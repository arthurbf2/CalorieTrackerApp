import { Component } from '@angular/core';
import { DefaultLoginLayoutComponent } from '../../components/default-login-layout/default-login-layout.component';
import { FormControl, FormGroup, FormRecord, ReactiveFormsModule, Validators } from '@angular/forms';
import { PrimaryInputComponent } from '../../components/primary-input/primary-input.component';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { SignupService } from '../../services/signup.service';

interface SignupForm {
  name: FormControl,
  email: FormControl,
  password: FormControl,
  passwordConfirm: FormControl
}

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    DefaultLoginLayoutComponent,
    ReactiveFormsModule,
    PrimaryInputComponent
  ],
  providers: [
    SignupService
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignUpComponent {
  signupForm!: FormGroup<SignupForm>;

  constructor(
    private router: Router,
    private signupService: SignupService,
    private toastService: ToastrService
  ){
    this.signupForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      passwordConfirm: new FormControl('', [Validators.required, Validators.minLength(6)]),
    })
  }

  submit(){
    if (this.signupForm.invalid) {
      this.toastService.error("Please fill out the fields correctly.")
      return;
    }

    if(this.signupForm.value.password !== this.signupForm.value.passwordConfirm) {
      this.toastService.error("Passwords do not match.")
      return;
    }

    const {name, email, password } = this.signupForm.value
    const signupData = {name, email, password}

    this.signupService.register(signupData).subscribe({
      next: () => {
        this.toastService.success("User registered successfully.")
        this.router.navigate(["/login"])
      },
      error: () => {
        this.toastService.error("Unexpected error, try again later.")
      }
    })
  }

  navigate(){
    this.router.navigate(["login"])
  }
}